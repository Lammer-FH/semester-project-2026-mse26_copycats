<template>

  <ion-card :class="['room-card', { disabled }]" @click="!disabled && goToDetail()">

    <div class="image-wrapper">
      <img :src="getRoomImageUrl(room.id)" class="room-image" :alt="room.name" />
      <ion-badge v-if="room.available !== undefined" :color="room.available ? 'success' : 'danger'" class="availability-badge">
        {{ room.available ? 'Available' : 'Unavailable' }}
      </ion-badge>
    </div>

    <ion-card-header>

      <ion-card-title>
        {{ room.name }}
      </ion-card-title>

    </ion-card-header>

    <ion-card-content>

      <p>{{ room.description }}</p>

      <RoomExtrasList :extras="room.extras" class="extras" />

    </ion-card-content>

  </ion-card>

</template>

<script lang="ts">
import {
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  IonBadge
} from '@ionic/vue'
import RoomExtrasList from '@/components/room/RoomExtrasList.vue'

import type { Room } from '@/models/Room'
import { getRoomImageUrl } from '@/models/Room'

export default {
  name: 'RoomCard',

  components: {
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardTitle,
    IonBadge,
    RoomExtrasList
  },

  props: {
    room: {
      type: Object as () => Room,
      required: true
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    getRoomImageUrl,
    goToDetail() {
      this.$router.push({ name: 'RoomDetail', params: { id: this.room.id } })
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

.image-wrapper {
  position: relative;
}

.availability-badge {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 0.72rem;
  padding: 4px 8px;
  border-radius: 6px;
  z-index: 1;
}

ion-card {
  border-radius: 14px;
  overflow: hidden;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.08);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

/* hover effect for desktop */
ion-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.12);
}

ion-card-title {
  font-size: 1.1rem;
  font-weight: 600;
  color: var(--ion-text-color);
}

ion-card-content {
  color: var(--ion-color-medium);
  font-size: 0.9rem;
}

.extras {
  margin-top: 12px;
}

.disabled {
  position: relative;
  cursor: not-allowed;
}

.disabled::after {
  content: '';
  position: absolute;
  inset: 0;
  background: rgba(0, 0, 0, 0.38);
  border-radius: 14px;
  pointer-events: none;
}

.disabled .room-image {
  filter: grayscale(0.7);
  opacity: 0.6;
}

.disabled:hover {
  transform: none;
}
</style>