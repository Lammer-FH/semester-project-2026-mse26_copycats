<template>
  <ion-page>

    <NavBar />

    <ion-content>

      <ion-grid>
        <ion-row>
            <h1>Available Rooms</h1>
            <DateRangePicker />
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
import DateRangePicker from '@/components/date/DateRangePicker.vue'
import roomService from '@/services/roomService'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'
import {
  IonPage,
  IonContent,
  IonGrid,
  IonRow,
  IonCol,
  IonButton,
  IonText,
} from '@ionic/vue'

import type { Room } from '@/models/Room'



export default {
  name: 'RoomOverview',

  components: {
    NavBar,
    IonPage,
    IonContent,
    IonGrid,
    IonRow,
    IonCol,
    IonButton,
    IonText,
    DateRangePicker,
    RoomCard
  },

  data(): { rooms: Room[], currentPage: number, roomsPerPage: number, loading: boolean } {
    return {
      currentPage: 1,
      roomsPerPage: 5,
      rooms: [] as Room[],
      loading: false
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

  }

}
</script>

<style>

</style>