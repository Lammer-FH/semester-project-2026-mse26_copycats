<template>
  <ion-page>

    <NavBar />

    <ion-content>

      <ion-grid class="room-grid">

        <ion-row>

          <ion-col
            v-for="room in rooms"
            :key="room.title"
            size="12"
            size-md="6"
            size-lg="5"
            size-xl="4"
          >
            <RoomCard :room="room" />
          </ion-col>

        </ion-row>

      </ion-grid>

    </ion-content>

  </ion-page>
</template>

<script lang="ts">
import NavBar from '@/components/navigation/NavBar.vue'
import RoomCard from '@/components/room/RoomCard.vue'
import {
  IonPage,
  IonContent
} from '@ionic/vue'

import type { Room } from '@/models/Room'

import classicDouble from '@/assets/rooms/classic-double.jpg'
import classicKing from '@/assets/rooms/classic-king.jpg'
import suiteDouble from '@/assets/rooms/suite-double.jpg'
import suiteKing from '@/assets/rooms/suite-king.jpg'
import presidentialSuite from '@/assets/rooms/presidential-suite.jpg'

import spa from '@/assets/icons/spa.png'
import minibar from '@/assets/icons/bar.png'
import wifi from '@/assets/icons/wifi.png'

export default {
  name: 'RoomOverview',

  components: {
    NavBar,
    IonPage,
    IonContent,
    RoomCard
  },

  data(): { rooms: Room[] } {
    return {
      rooms: [
        {
          title: 'Classic Double',
          description: 'Cozy room with double bed',
          imagePath: classicDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi }
          ]
        },
        {
          title: 'Classic Double Sea View',
          description: 'Cozy room with double bed and sea view',
          imagePath: classicDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          title: 'Classic King',
          description: 'Spacious room with king bed',
          imagePath: classicKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          title: 'Classic King Sea View',
          description: 'Spacious room with king bed and sea view',
          imagePath: classicKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          title: 'Suite Double',
          description: 'Luxury suite with double bed',
          imagePath: suiteDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          title: 'Suite Double Sea View',
          description: 'Luxury suite with double bed and sea view',
          imagePath: suiteDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          title: 'Suite King',
          description: 'Luxury suite with king bed',
          imagePath: suiteKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 2, name: 'Spa', iconPath: spa }
          ]
        },
        {
          title: 'Suite King Sea View',
          description: 'Luxury suite with king bed and sea view',
          imagePath: suiteKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 2, name: 'Spa', iconPath: spa }
          ]
        },
        {
          title: 'Presidential Suite',
          description: 'Ultimate Presidential Suite with king bed',
          imagePath: presidentialSuite,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 2, name: 'Spa', iconPath: spa }
          ]
        }
      ]
    }
  }
}
</script>

<style>
    
</style>