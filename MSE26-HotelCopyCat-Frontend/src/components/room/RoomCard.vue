<template>

  <ion-card>

    <img :src="room.imagePath" class="room-image" />

    <ion-card-header>

      <ion-card-title>
        {{ room.title }}
      </ion-card-title>

    </ion-card-header>

    <ion-card-content>

      <p>{{ room.description }}</p>

      <div class="extras">
        <img
          v-for="extra in room.extras"
          :key="extra.id"
          :src="extra.iconPath"
          class="extra-icon"
          :title="extra.name"
        />
      </div>

    </ion-card-content>

  </ion-card>

</template>

<script lang="ts">
import {
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle
} from '@ionic/vue'

import type { Room } from '@/models/Room'

export default {
  name: 'RoomCard',

  components: {
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardTitle
  },

  props: {
    room: {
      type: Object as () => Room,
      required: true
    }
  }
}
</script>

<style scoped>
.room-image {
  width: 100%;
  height: 180px;
  object-fit: cover;
  border-top-left-radius: 14px;
  border-top-right-radius: 14px;
}

ion-card {
  background: #d3d3d3;
  border-radius: 14px;
  overflow: hidden;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.08);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

/* hover effect for desktop */
ion-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.12);
}

ion-card-title {
  font-size: 1.1rem;
  font-weight: 600;
  color: #222;
}

ion-card-content {
  color: #555;
  font-size: 0.9rem;
}

.extras {
  display: flex;
  gap: 8px;
  margin-top: 12px;
  flex-wrap: wrap;
}

.extra-icon {
  width: 22px;
  height: 22px;
  opacity: 0.85;
}
</style>