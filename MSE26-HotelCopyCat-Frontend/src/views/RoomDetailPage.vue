<template>
  <ion-page>

    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button default-href="/rooms" />
        </ion-buttons>
        <ion-title>{{ room?.name ?? 'Room Details' }}</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <div v-if="room" class="detail-container">
        <img :src="getRoomImageUrl(room.id)" :alt="room.name" class="room-image" />

        <div class="detail-content">
          <h1 class="room-title">{{ room.name }}</h1>
          <p class="room-description">{{ room.description }}</p>

          <div class="availability-section">
            <template v-if="bookingStore.startDate && bookingStore.endDate">
              <ion-text class="date-range-label">
                {{ formatDate(bookingStore.startDate) }} &ndash; {{ formatDate(bookingStore.endDate) }}
              </ion-text>
              <div class="availability-status">
                <ion-spinner v-if="availabilityLoading" name="crescent" />
                <template v-else-if="availability !== null">
                  <AvailabilityBadge :available="availability" />
                  <ion-button :disabled="!availability" expand="block" class="book-button" @click="goToBooking">
                    Book now
                  </ion-button>
                </template>
              </div>
            </template>
            <template v-else>
              <ion-text color="medium" class="no-dates-hint">
                No dates selected - <router-link to="/rooms">select dates</router-link>
              </ion-text>
            </template>
          </div>

          <ion-label>Amenities</ion-label>
          <RoomExtrasList :extras="room.extras" class="extras-section" />
        </div>
      </div>

      <div v-else class="error-container">
        <ion-text color="danger">Room not found.</ion-text>
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
  IonLabel,
  IonText,
  IonButton,
  IonSpinner
} from '@ionic/vue'
import RoomExtrasList from '@/components/room/RoomExtrasList.vue'
import AvailabilityBadge from '@/components/common/AvailabilityBadge.vue'
import roomService from '@/services/roomService'
import type { Room } from '@/models/Room'
import { getRoomImageUrl } from '@/models/Room'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'

export default {
  name: 'RoomDetailPage',

  components: {
    IonPage,
    IonHeader,
    IonToolbar,
    IonButtons,
    IonBackButton,
    IonTitle,
    IonContent,
    IonLabel,
    IonText,
    IonButton,
    IonSpinner,
    RoomExtrasList,
    AvailabilityBadge
  },

  data() {
    return {
      room: null as Room | null,
      availability: null as boolean | null,
      availabilityLoading: false,
      bookingStore: useBookingPeriodStore()
    }
  },

  async created() {
    const id = Number(this.$route.params.id)
    this.room = await roomService.getRoomTypeById(id)
    await this.checkAvailability()
  },
    
  async ionViewWillEnter() {
    await this.checkAvailability()
  },

  watch: {
    bookingStore: {
      handler() {
        this.checkAvailability()
      },
      deep: true
    }
  },

  methods: {
    getRoomImageUrl,

    async checkAvailability() {
      if (!this.room || !this.bookingStore.startDate || !this.bookingStore.endDate) return
      this.availabilityLoading = true
      try {
        this.availability = await roomService.getAvailability(
          this.room.id,
          this.bookingStore.startDate,
          this.bookingStore.endDate
        )
      } finally {
        this.availabilityLoading = false
      }
    },

    goToBooking() {
      if (!this.room || !this.availability) {
        return
      }

      this.$router.push({ name: 'Booking', params: { id: this.room.id } })
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
.error-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 60vh;
}

.room-image {
  width: 100%;
  max-height: 280px;
  object-fit: cover;
}

.detail-content {
  padding: 16px;
}

.room-title {
  font-size: 1.5rem;
  font-weight: 700;
  color: var(--ion-text-color, #222);
  margin: 0 0 8px;
}

.room-description {
  font-size: 1rem;
  color: var(--ion-color-medium, #555);
  line-height: 1.6;
  margin: 0 0 20px;
}

.extras-section {
  margin-top: 8px;
}

.availability-section {
  margin-bottom: 20px;
  padding: 12px 14px;
  background: var(--ion-color-light, #f4f4f4);
  border-radius: 10px;
}

.date-range-label {
  display: block;
  font-size: 0.85rem;
  color: var(--ion-color-medium);
  margin-bottom: 10px;
}

.availability-status {
  display: flex;
  align-items: center;
  gap: 12px;
  flex-wrap: wrap;
}

.book-button {
  flex: 1;
  min-width: 140px;
  margin: 0;
}

.no-dates-hint {
  font-size: 0.9rem;
}

.no-dates-hint a {
  color: var(--ion-color-primary);
  text-decoration: none;
}
</style>
