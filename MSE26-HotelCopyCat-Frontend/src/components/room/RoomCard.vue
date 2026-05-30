<template>

  <ion-card :class="['room-card', { disabled }]" @click="!disabled && goToDetail()">

    <img :src="getRoomImageUrl(room.id)" class="room-image" :alt="room.name" />

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
  IonCardTitle
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
  opacity: 0.5;
  filter: grayscale(0.8);
}

.disabled:hover {
  transform: none;
}
</style>