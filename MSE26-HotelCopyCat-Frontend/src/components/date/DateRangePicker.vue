<template>
    
    <ion-datetime-button datetime="startdate"></ion-datetime-button>

    <ion-modal :keep-contents-mounted="true">
        <ion-datetime 
        id="startdate" 
        presentation="date" 
        :min="today"
        v-model="bookingStore.startDate">
        </ion-datetime>
    </ion-modal>

    <ion-datetime-button datetime="enddate"></ion-datetime-button>

    <ion-modal :keep-contents-mounted="true">
        <ion-datetime 
        id="enddate" 
        presentation="date" 
        :min="bookingStore.startDate || today" 
        v-model="bookingStore.endDate">
        </ion-datetime>
    </ion-modal>
</template>

<script lang="ts">
import {
    IonPage,
    IonContent,
    IonDatetime,
    IonDatetimeButton,
    IonModal
} from '@ionic/vue'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'

export default {
    name: 'DateRangePicker',

    components: {
        IonPage,
        IonContent,
        IonDatetime,
        IonDatetimeButton,
        IonModal
    },
    data() {
        return {
            bookingStore: useBookingPeriodStore()
        }
    },
    computed: {
        /* get current date to use as min value for datepicker */
        today(): string {
            return new Date().toISOString().split('T')[0]
        }
    }
}
</script>