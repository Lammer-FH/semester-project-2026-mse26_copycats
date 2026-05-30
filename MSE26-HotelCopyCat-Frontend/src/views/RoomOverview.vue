<template>
    <ion-page>

        <NavBar />

        <ion-content>

            <ion-grid>
                <ion-row>
                    <h1>Available Rooms</h1>
                </ion-row>
                <ion-row>
                    <DateRangePicker />
                </ion-row>

                <ion-row>

                    <template v-if="!loading">
                        <ion-col v-for="room in rooms" :key="room.title" size="12" size-md="6" size-lg="5" size-xl="4">
                            <RoomCard :room="room" />
                        </ion-col>
                    </template>
                    <template v-if="loading">
                      <ion-col>
                          <ion-spinner name="circles" />
                      </ion-col>
                    </template>

                </ion-row>

                <ion-row>
                    <ion-col>
                        <ion-button :disabled="currentPage === 0" @click="previousPage">
                            Previous
                        </ion-button>

                        <ion-text>
                            Page {{ currentPage + 1 }} / {{ totalPages }}
                        </ion-text>

                        <ion-button :disabled="currentPage === totalPages - 1" @click="nextPage">
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
    IonSpinner
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
        IonSpinner,
        DateRangePicker,
        RoomCard
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
                if (bookingStore.startDate && bookingStore.endDate){

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

                    fetchedRooms = availabilityResults.filter(r => r.available)
                    this.totalPages = Math.ceil(fetchedRooms.length / this.roomsPerPage)
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