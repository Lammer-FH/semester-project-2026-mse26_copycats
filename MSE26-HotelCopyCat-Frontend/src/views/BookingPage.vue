<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button :default-href="backHref" />
        </ion-buttons>
        <ion-title>Booking</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <div class="booking-page">
        <ion-card v-if="guestBookingStore.loadingRoom" class="message-card message-card-center">
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
          v-else-if="guestBookingStore.submitError"
          variant="error"
          subtitle="Error"
          title="Booking could not be submitted"
          :message="guestBookingStore.submitError"
        />

        <BookingConfirmationCard
          v-else-if="guestBookingStore.step === 'confirmation' && guestBookingStore.confirmation"
          :confirmation="guestBookingStore.confirmation"
        />

        <ion-grid v-else-if="bookingPeriodStore.isValidPeriod && guestBookingStore.hasRoom" class="booking-layout">
          <ion-row>
            <ion-col size="12" size-lg="4">
              <BookingSummaryCard
                :room="guestBookingStore.room"
                :period-label="periodLabel"
                :duration-in-days="bookingPeriodStore.durationInDays"
              />
            </ion-col>

            <ion-col size="12" size-lg="8">
              <BookingGuestForm
                v-if="guestBookingStore.step === 'form'"
                :form="guestBookingStore.form"
                :errors="guestBookingStore.errors"
                @update-booking-field="updateBookingField"
                @validate-booking-field="validateBookingField"
                @review-booking="reviewBooking"
              />

              <BookingReviewCard
                v-else-if="guestBookingStore.step === 'review'"
                :room="guestBookingStore.room"
                :form="guestBookingStore.form"
                :period-label="periodLabel"
                :duration-in-days="bookingPeriodStore.durationInDays"
                :submitting="guestBookingStore.submitting"
                @edit-booking="guestBookingStore.goToForm()"
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
                :message="roomStatusMessage"
                action-label="Back to rooms"
                action-route="/rooms"
              />
            </ion-col>
          </ion-row>
        </ion-grid>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonButtons,
  IonBackButton,
  IonTitle,
  IonContent,
  IonGrid,
  IonRow,
  IonCol,
  IonCard,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonCardContent,
  IonButton,
  IonSpinner
} from '@ionic/vue'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'
import { useGuestBookingStore } from '@/stores/useGuestBookingStore'
import BookingSummaryCard from '@/components/booking/BookingSummaryCard.vue'
import BookingGuestForm from '@/components/booking/BookingGuestForm.vue'
import BookingReviewCard from '@/components/booking/BookingReviewCard.vue'
import BookingConfirmationCard from '@/components/booking/BookingConfirmationCard.vue'
import StatusCard from '@/components/common/StatusCard.vue'

export default {
  name: 'BookingPage',

  components: {
    IonPage,
    IonHeader,
    IonToolbar,
    IonButtons,
    IonBackButton,
    IonTitle,
    IonContent,
    IonGrid,
    IonRow,
    IonCol,
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonCardContent,
    IonButton,
    IonSpinner,
    BookingSummaryCard,
    BookingGuestForm,
    BookingReviewCard,
    BookingConfirmationCard,
    StatusCard
  },

  data() {
    return {
      bookingPeriodStore: useBookingPeriodStore(),
      guestBookingStore: useGuestBookingStore()
    }
  },

  computed: {
    backHref(): string {
      const roomId = Number(this.$route.params.id)
      return Number.isNaN(roomId) ? '/rooms' : `/rooms/${roomId}`
    },

    periodLabel(): string {
      if (!this.bookingPeriodStore.startDate || !this.bookingPeriodStore.endDate) {
        return ''
      }

      return `${this.formatDate(this.bookingPeriodStore.startDate)} - ${this.formatDate(this.bookingPeriodStore.endDate)}`
    },

    roomStatusMessage(): string {
      if (this.guestBookingStore.roomLoadError) {
        return this.guestBookingStore.roomLoadError
      }

      if (!this.bookingPeriodStore.isValidPeriod) {
        return 'Please select a valid booking period before continuing.'
      }

      return 'The selected room could not be loaded.'
    }
  },

  async created() {
    const id = Number(this.$route.params.id)
    await this.guestBookingStore.loadBookingContext(id)
  },

  methods: {
    updateBookingField(payload: { field: 'firstName' | 'lastName' | 'email' | 'confirmEmail' | 'breakfast', value: string | boolean }) {
      this.guestBookingStore.updateField(payload.field, payload.value)
    },

    validateBookingField(field: 'firstName' | 'lastName' | 'email' | 'confirmEmail' | 'breakfast') {
      this.guestBookingStore.validateField(field, true)
    },

    reviewBooking() {
      this.guestBookingStore.goToReview()
    },

    async confirmBooking() {
      await this.guestBookingStore.submitBooking()
    },

    formatDate(dateStr: string): string {
      return new Date(dateStr).toLocaleDateString('de-AT', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
      })
    }
  }
}
</script>

<style scoped>
.booking-page {
  padding: 16px;
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
