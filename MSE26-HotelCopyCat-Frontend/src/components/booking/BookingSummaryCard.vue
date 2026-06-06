<template>
  <ion-card class="booking-card">
    <ion-card-header>
      <ion-card-subtitle>Selected room</ion-card-subtitle>
      <ion-card-title>{{ room?.name ?? 'Booking' }}</ion-card-title>
    </ion-card-header>

    <ion-card-content>
      <p v-if="room" class="description">{{ room.description }}</p>
      <p class="summary-row">
        <strong>Period:</strong>
        {{ periodLabel || 'No booking period selected yet.' }}
      </p>
      <p class="summary-row">
        <strong>Nights:</strong>
        {{ durationInDays }}
      </p>
    </ion-card-content>
  </ion-card>
</template>

<script lang="ts">
import {
  IonCard,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonCardContent
} from '@ionic/vue'
import type { PropType } from 'vue'
import type { BookingRoomContext } from '@/models/BookingRoomContext'

export default {
  name: 'BookingSummaryCard',

  components: {
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonCardContent
  },

  props: {
    room: {
      type: Object as PropType<BookingRoomContext | null>,
      default: null
    },
    periodLabel: {
      type: String,
      default: ''
    },
    durationInDays: {
      type: Number,
      default: 0
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

.description,
.summary-row {
  color: var(--ion-color-medium);
  line-height: 1.6;
}
</style>
