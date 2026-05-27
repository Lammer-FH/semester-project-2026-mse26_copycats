<template>
  <ion-page>

    <NavBar />

    <ion-content>

      <ion-grid>
        <ion-row>
            <ion-datetime-button datetime="startdatetime"></ion-datetime-button>

            <ion-modal :keep-contents-mounted="true">
                <ion-datetime id="startdatetime"
                presentation="date"
                :min="today"
                ></ion-datetime>
            </ion-modal>

            <ion-datetime-button datetime="enddatetime"></ion-datetime-button>

            <ion-modal :keep-contents-mounted="true">
                <ion-datetime id="enddatetime"
                presentation="date"
                :min="today"
                ></ion-datetime>
            </ion-modal>
        </ion-row>

        <ion-row>

          <ion-col
            v-for="room in paginatedRooms"
            :key="room.title"
            size="12"
            size-md="6"
            size-lg="5"
            size-xl="4"
          >
            <RoomCard :room="room" />
          </ion-col>

        </ion-row>

        <ion-row>
            <ion-col>
                <ion-button
                :disabled="currentPage === 1"
                @click="currentPage--"
                >
                    Previous
                </ion-button>

                <ion-text>
                    Page {{ currentPage }} / {{ totalPages }}
                </ion-text>

                <ion-button
                    :disabled="currentPage === totalPages"
                    @click="currentPage++"
                >
                    Next
                </ion-button>
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
  IonContent,
  IonDatetime,
  IonDatetimeButton
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
    IonDatetime,
    IonDatetimeButton,
    RoomCard
  },

  data(): { rooms: Room[], currentPage: number, roomsPerPage: number } {
    return {
      currentPage: 1,
      roomsPerPage: 5,
      // TODO: remove once rooms are fetched from api
      rooms: [
        {
          id: 0,
          title: 'Classic Double',
          description: 'Cozy room with double bed',
          imagePath: classicDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi }
          ]
        },
        {
          id: 1,
          title: 'Classic Double Sea View',
          description: 'Cozy room with double bed and sea view',
          imagePath: classicDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 2,
          title: 'Classic King',
          description: 'Spacious room with king bed',
          imagePath: classicKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 3,
          title: 'Classic King Sea View',
          description: 'Spacious room with king bed and sea view',
          imagePath: classicKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 4,
          title: 'Suite Double',
          description: 'Luxury suite with double bed',
          imagePath: suiteDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 5,
          title: 'Suite Double Sea View',
          description: 'Luxury suite with double bed and sea view',
          imagePath: suiteDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 6,
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
          id: 7,
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
          id: 8,
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
  },

  computed: {
    /* get rooms of current page */
      paginatedRooms(): Room[] {
        const start = (this.currentPage - 1) * this.roomsPerPage //e.g: start at index 0
        const end = start + this.roomsPerPage //take room until index 5 (not included) -> 0,1,2,3,4

        return this.rooms.slice(start, end)
    },

    /* calculate total amount of pages */
    totalPages(): number {
        return Math.ceil(this.rooms.length / this.roomsPerPage)
    },

    /* get current date to use as min value for datepicker */
    today(): string {
    return new Date().toISOString().split('T')[0]
    }
  }

}
</script>

<style>

</style>