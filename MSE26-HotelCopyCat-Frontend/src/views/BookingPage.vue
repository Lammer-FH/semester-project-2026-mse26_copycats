<template>
  <PageLayout :class="{ 'booking-page-shell': step === BOOKING_STEP.CONFIRMATION && confirmation }">
    <template #banner>
      <ion-header>
        <ion-toolbar>
          <ion-buttons slot="start">
            <ion-back-button :default-href="backHref" />
          </ion-buttons>
          <ion-title>Booking</ion-title>
        </ion-toolbar>
      </ion-header>
    </template>

    <div class="booking-page">
      <ion-card v-if="roomLoading" class="message-card message-card-center">
        <ion-card-header>
          <ion-card-subtitle>Loading</ion-card-subtitle>
          <ion-card-title>Booking details</ion-card-title>
        </ion-card-header>

        <ion-card-content>
          <ion-spinner name="crescent" />
          <p>Loading booking details...</p>
        </ion-card-content>
      </ion-card>

      <StatusCard
        v-else-if="roomError"
        variant="error"
        subtitle="Booking not available"
        title="Booking not ready"
        :message="roomError"
        action-label="Back to rooms"
        action-route="/rooms"
      />

      <StatusCard
        v-else-if="submitError"
        variant="error"
        subtitle="Error"
        title="Booking could not be submitted"
        :message="submitError"
      />

      <BookingConfirmationCard
        v-else-if="step === BOOKING_STEP.CONFIRMATION && confirmation"
        :confirmation="confirmation"
      />

      <ion-grid v-else-if="room && bookingPeriodStore.isValidPeriod" class="booking-layout">
        <ion-row>
          <ion-col size="12" size-lg="4">
            <BookingSummaryCard
              :room="room"
              :period-label="periodLabel"
              :duration-in-days="bookingPeriodStore.durationInDays"
            />
          </ion-col>

          <ion-col size="12" size-lg="8">
            <BookingGuestForm
              v-if="step === BOOKING_STEP.FORM"
              :form="form"
              :errors="errors"
              @update-booking-field="updateBookingField"
              @review-booking="reviewBooking"
            />

            <BookingReviewCard
              v-else-if="step === BOOKING_STEP.REVIEW"
              :room="room"
              :form="form"
              :period-label="periodLabel"
              :duration-in-days="bookingPeriodStore.durationInDays"
              :submitting="submitting"
              @edit-booking="goToForm"
              @confirm-booking="confirmBooking"
            />
          </ion-col>
        </ion-row>
      </ion-grid>

      <ion-grid v-else>
        <ion-row>
          <ion-col size="12" size-lg="8" offset-lg="2">
            <StatusCard
              variant="error"
              subtitle="Booking not available"
              title="Booking not ready"
              :message="availabilityMessage"
              action-label="Back to rooms"
              action-route="/rooms"
            />
          </ion-col>
        </ion-row>
      </ion-grid>
    </div>
  </PageLayout>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import {
  IonBackButton,
  IonButtons,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonCol,
  IonGrid,
  IonHeader,
  IonRow,
  IonSpinner,
  IonTitle,
  IonToolbar
} from '@ionic/vue'
import BookingConfirmationCard from '@/components/booking/BookingConfirmationCard.vue'
import BookingGuestForm from '@/components/booking/BookingGuestForm.vue'
import BookingReviewCard from '@/components/booking/BookingReviewCard.vue'
import BookingSummaryCard from '@/components/booking/BookingSummaryCard.vue'
import StatusCard from '@/components/common/StatusCard.vue'
import PageLayout from '@/components/layout/PageLayout.vue'
import { BOOKING_STEP, type BookingStep } from '@/features/booking/bookingStep'
import type { BookingConfirmation } from '@/models/BookingConfirmation'
import { useBookingForm } from '@/features/booking/useBookingForm'
import { toBookingConfirmation, toBookingRoomContext } from '@/features/booking/bookingMapper'
import roomService from '@/services/roomService'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'
import type { BookingRoomContext } from '@/models/BookingRoomContext'

const route = useRoute()
const bookingPeriodStore = useBookingPeriodStore()

const room = ref<BookingRoomContext | null>(null)
const roomLoading = ref(false)
const roomError = ref('')

const step = ref<BookingStep>(BOOKING_STEP.FORM)
const confirmation = ref<BookingConfirmation | null>(null)

const {
  form,
  errors,
  submitError,
  submitting,
  resetBookingForm,
  updateBookingField,
  validateForm,
  submitBooking
} = useBookingForm()

const backHref = computed(() => {
  const roomId = Number(route.params.id)
  return Number.isNaN(roomId) ? '/rooms' : `/rooms/${roomId}`
})

const periodLabel = computed(() => {
  if (!bookingPeriodStore.startDate || !bookingPeriodStore.endDate) {
    return ''
  }

  return `${formatDate(bookingPeriodStore.startDate)} - ${formatDate(bookingPeriodStore.endDate)}`
})

const availabilityMessage = computed(() => {
  if (roomError.value) {
    return roomError.value
  }

  if (!bookingPeriodStore.isValidPeriod) {
    return 'Please select a valid booking period before continuing.'
  }

  return 'The selected room could not be loaded.'
})

onMounted(async () => {
  resetBookingFlow()
  await loadRoom()
})

function resetBookingFlow() {
  resetBookingForm()
  step.value = BOOKING_STEP.FORM
  confirmation.value = null
}

async function loadRoom() {
  roomLoading.value = true
  roomError.value = ''

  try {
    const id = Number(route.params.id)
    const loadedRoom = await roomService.getRoomTypeById(id)

    if (!loadedRoom) {
      room.value = null
      roomError.value = 'The selected room could not be loaded.'
      return
    }

    room.value = toBookingRoomContext(loadedRoom)
  } catch {
    room.value = null
    roomError.value = 'The selected room could not be loaded. Please go back and try again.'
  } finally {
    roomLoading.value = false
  }
}

function reviewBooking() {
  submitError.value = ''

  if (validateForm()) {
    step.value = BOOKING_STEP.REVIEW
  }
}

function goToForm() {
  step.value = BOOKING_STEP.FORM
}

function isBookingReady() {
  if (!room.value || !bookingPeriodStore.startDate || !bookingPeriodStore.endDate) {
    submitError.value = 'Room and booking period are required before submitting.'
    return false
  }

  if (!bookingPeriodStore.isValidPeriod) {
    submitError.value = 'Please select a valid booking period before submitting.'
    return false
  }

  return true
}

async function confirmBooking() {
  if (!isBookingReady() || !room.value || !bookingPeriodStore.startDate || !bookingPeriodStore.endDate) {
    return false
  }

  const response = await submitBooking(room.value, {
    checkIn: bookingPeriodStore.startDate,
    checkOut: bookingPeriodStore.endDate
  })

  if (!response) {
    step.value = BOOKING_STEP.FORM
    return false
  }

  confirmation.value = toBookingConfirmation(response)
  step.value = BOOKING_STEP.CONFIRMATION
  return true
}

function formatDate(dateStr: string): string {
  return new Date(dateStr).toLocaleDateString('de-AT', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric'
  })
}
</script>

<style scoped>
.booking-page {
  padding: 6px;
}

.booking-layout {
  max-width: 920px;
  margin: 0 auto;
}

.message-card {
  border-radius: 16px;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
  max-width: 720px;
  margin: 0 auto;
}

.message-card-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  text-align: center;
}

.action-button {
  margin-top: 16px;
}

@media (min-width: 768px) {
  .booking-layout {
    grid-template-columns: minmax(280px, 360px) minmax(0, 1fr);
    align-items: start;
  }
}
</style>
