<template>
  <ion-card class="booking-card">
    <ion-card-header>
      <ion-card-subtitle>Booking confirmed</ion-card-subtitle>
      <ion-card-title>{{ confirmation.title }}</ion-card-title>
    </ion-card-header>

    <ion-card-content>
      <p class="confirmation-row">
        {{ confirmation.message }}
      </p>

      <ion-button expand="block" class="action-button" :router-link="confirmation.actionRoute">
        {{ confirmation.actionLabel }}
      </ion-button>
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
  IonButton
} from '@ionic/vue'
import type { PropType } from 'vue'
import type { BookingConfirmation } from '@/models/BookingConfirmation'

export default {
  name: 'BookingConfirmationCard',

  components: {
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonCardContent,
    IonButton
  },

  props: {
    confirmation: {
      type: Object as PropType<BookingConfirmation>,
      required: true
    }
  }
}
</script>

<style scoped>
.booking-card {
  margin: 0;
  border-radius: 16px;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
}

.confirmation-row {
  margin: 0 0 10px;
  color: var(--ion-color-medium);
}

.action-button {
  margin-top: 18px;
}
</style>
