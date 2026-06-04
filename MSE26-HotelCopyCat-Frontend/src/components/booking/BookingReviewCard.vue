<template>
  <ion-card class="booking-card">
    <ion-card-header>
      <ion-card-subtitle>Review</ion-card-subtitle>
      <ion-card-title>Check your booking details</ion-card-title>
    </ion-card-header>

    <ion-card-content>
      <p class="intro">Review the details below before you confirm the booking.</p>

      <div class="review-block">
        <h3>Stay</h3>
        <p><strong>{{ room?.name }}</strong></p>
        <p>{{ periodLabel }}</p>
        <p>{{ durationInDays }} night(s)</p>
      </div>

      <div class="review-block">
        <h3>Guest</h3>
        <p>{{ form.firstName }} {{ form.lastName }}</p>
        <p>{{ form.email }}</p>
        <p>Breakfast: {{ form.breakfast ? 'Yes' : 'No' }}</p>
      </div>

      <div class="button-row">
        <ion-button fill="outline" @click="$emit('edit-booking')">
          Edit details
        </ion-button>
        <ion-button :disabled="submitting" @click="$emit('confirm-booking')">
          <ion-spinner v-if="submitting" name="crescent" />
          <span v-else>Confirm booking</span>
        </ion-button>
      </div>
    </ion-card-content>
  </ion-card>
</template>

<script lang="ts">
import {
  IonCard,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonCardContent,
  IonButton,
  IonSpinner
} from '@ionic/vue'
import type { PropType } from 'vue'
import type { BookingForm } from '@/models/BookingForm'
import type { BookingRoomContext } from '@/models/BookingRoomContext'

export default {
  name: 'BookingReviewCard',

  components: {
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonCardContent,
    IonButton,
    IonSpinner
  },

  props: {
    room: {
      type: Object as PropType<BookingRoomContext | null>,
      default: null
    },
    form: {
      type: Object as PropType<BookingForm>,
      required: true
    },
    periodLabel: {
      type: String,
      default: ''
    },
    durationInDays: {
      type: Number,
      default: 0
    },
    submitting: {
      type: Boolean,
      default: false
    }
  },

  emits: ['edit-booking', 'confirm-booking']
}
</script>

<style scoped>
.booking-card {
  margin: 0;
  border-radius: 16px;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
}

.review-block + .review-block {
  margin-top: 20px;
}

.intro {
  margin: 0 0 16px;
  color: var(--ion-color-medium);
  line-height: 1.6;
}

.review-block h3 {
  margin: 0 0 8px;
  font-size: 1rem;
}

.review-block p {
  margin: 0 0 6px;
  color: var(--ion-color-medium);
}

.button-row {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  margin-top: 20px;
}
</style>
