<template>
    
    <ion-datetime-button datetime="startdate"></ion-datetime-button>

    <ion-modal :keep-contents-mounted="true">
        <ion-datetime
        id="startdate"
        presentation="date" 
        :min="today"
        :value="bookingStore.startDate ?? today"
        @ionChange="(e: any) => bookingStore.startDate = e.detail.value"
        />
    </ion-modal>

    <ion-datetime-button datetime="enddate"></ion-datetime-button>

    <ion-modal :keep-contents-mounted="true">
        <ion-datetime
        id="enddate" 
        presentation="date"
        :min="bookingStore.startDate || today" 
        :value="bookingStore.endDate ?? today"
        @ionChange="(e: any) => bookingStore.endDate = e.detail.value"
        />
    </ion-modal>

    <ion-button @click="resetDates">
        Reset
    </ion-button>
</template>

<script lang="ts">
import {
    IonPage,
    IonContent,
    IonDatetime,
    IonDatetimeButton,
    IonModal,
    IonButton
} from '@ionic/vue'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'

export default {
    name: 'DateRangePicker',

    components: {
        IonPage,
        IonContent,
        IonDatetime,
        IonDatetimeButton,
        IonModal,
        IonButton
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
    },
    methods: {
        async resetDates() {
            const bookingStore = useBookingPeriodStore()
            bookingStore.clearBookingPeriod()
        }
    }
}
</script>