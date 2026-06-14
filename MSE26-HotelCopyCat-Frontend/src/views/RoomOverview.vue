<template>
  <PageLayout>
    <PageContentContainer>
      <ion-row>
        <h1>Available Rooms</h1>
      </ion-row>
      <ion-row>
        <DateRangePicker/>
      </ion-row>

      <ion-row>

        <template v-if="!loading">
          <ion-col v-for="room in rooms" :key="room.name" size="12" size-md="6" size-lg="5" size-xl="4">
            <RoomCard
                :room="room"
                :disabled="room.available === false"
            />
          </ion-col>
        </template>
        <template v-if="loading">
          <ion-col>
            <ion-spinner name="circles"/>
          </ion-col>
        </template>

      </ion-row>

      <PaginationControls
          :current-page="currentPage"
          :total-pages="totalPages"
          @previous="previousPage"
          @next="nextPage"
      />
    </PageContentContainer>
  </PageLayout>
</template>

<script lang="ts">
import PageLayout from '@/components/layout/PageLayout.vue'
import PageContentContainer from '@/components/layout/PageContentContainer.vue'
import RoomCard from '@/components/room/RoomCard.vue'
import DateRangePicker from '@/components/date/DateRangePicker.vue'
import PaginationControls from '@/components/navigation/PaginationControls.vue'
import roomService from '@/services/roomService'
import {useBookingPeriodStore} from '@/stores/useBookingPeriodStore'
import {
  IonCol,
  IonRow,
  IonSpinner
} from '@ionic/vue'

import type {Room} from '@/models/Room'


export default {
  name: 'RoomOverview',

  components: {
    PageLayout,
    IonRow,
    IonCol,
    IonSpinner,
    DateRangePicker,
    RoomCard,
    PaginationControls
  },

  data() {
    return {
      currentPage: 0,
      roomsPerPage: 5,

      rooms: [] as Room[],
      booking: useBookingPeriodStore(),

      totalPages: 0,

      loading: false
    }
  },
  async mounted() {
    await this.loadRooms()
  },
  methods: {
    async fetchRooms(page: number) {
      return await roomService.getRoomTypes(page, this.roomsPerPage)
    },
    async loadRooms() {

      this.loading = true

      try {

        const bookingStore = useBookingPeriodStore()

        const response = await this.fetchRooms(this.currentPage)

        let fetchedRooms = response.rooms
        this.totalPages = response.totalPages

        //do availability check if date range was set
        if (bookingStore.startDate && bookingStore.endDate) {

          const availabilityResults = await Promise.all(
              fetchedRooms.map(async (room: Room) => {

                const available = await roomService.getAvailability(
                    room.id,
                    bookingStore.startDate,
                    bookingStore.endDate
                )

                return {
                  ...room,
                  available
                }
              })
          )
          fetchedRooms = availabilityResults
        }

        this.rooms = fetchedRooms

      } catch (error) {
        console.error(error)
      } finally {
        this.loading = false
      }
    },

    async nextPage() {

      if (this.currentPage < this.totalPages - 1) {

        this.currentPage++

        await this.loadRooms()
      }
    },

    async previousPage() {

      if (this.currentPage > 0) {

        this.currentPage--

        await this.loadRooms()
      }
    }
  },
  watch: {
    booking: {
      handler() {
        this.loadRooms()
      },
      deep: true
    }
  }

}
</script>

<style></style>