<template>
  <ion-page>

    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button default-href="/rooms" />
        </ion-buttons>
        <ion-title>{{ room?.title ?? 'Room Details' }}</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <div v-if="room" class="detail-container">
        <img :src="room.imagePath" :alt="room.title" class="room-image" />

        <div class="detail-content">
          <h1 class="room-title">{{ room.title }}</h1>
          <p class="room-description">{{ room.description }}</p>
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
  IonText
} from '@ionic/vue'
import RoomExtrasList from '@/components/room/RoomExtrasList.vue'
import roomService from '@/services/roomService'
import type { Room } from '@/models/Room'

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
    RoomExtrasList
  },

  data() {
    return {
      room: null as Room | null,
    }
  },

  async created() {
    const id = Number(this.$route.params.id)
    this.room = await roomService.getRoomTypeById(id)
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
</style>
