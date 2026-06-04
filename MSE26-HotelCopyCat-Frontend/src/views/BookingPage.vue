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
        <div class="booking-card">
          <h1>Booking Page</h1>
          <p v-if="room">
            You selected <strong>{{ room.name }}</strong>.
          </p>
          <p v-if="bookingStore.startDate && bookingStore.endDate">
            Period: {{ formatDate(bookingStore.startDate) }} - {{ formatDate(bookingStore.endDate) }}
          </p>
          <p v-else>
            No booking period selected yet.
          </p>
        </div>
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
  IonContent
} from '@ionic/vue'
import roomService from '@/services/roomService'
import type { Room } from '@/models/Room'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'

export default {
  name: 'BookingPage',

  components: {
    IonPage,
    IonHeader,
    IonToolbar,
    IonButtons,
    IonBackButton,
    IonTitle,
    IonContent
  },

  data() {
    return {
      room: null as Room | null,
      bookingStore: useBookingPeriodStore()
    }
  },

  computed: {
    backHref(): string {
      const roomId = Number(this.$route.params.id)
      return Number.isNaN(roomId) ? '/rooms' : `/rooms/${roomId}`
    }
  },

  async created() {
    const id = Number(this.$route.params.id)
    this.room = await roomService.getRoomTypeById(id)
  },

  methods: {
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

.booking-card {
  max-width: 720px;
  margin: 0 auto;
  padding: 24px;
  border-radius: 16px;
  background: #fff;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
}
</style>
