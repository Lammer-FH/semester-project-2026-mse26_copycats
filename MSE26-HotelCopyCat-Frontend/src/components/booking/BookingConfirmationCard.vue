<template>
  <div class="booking-card">
  <ion-card class="booking-card">
    <ion-card-header>
      <ion-card-title>Your booking</ion-card-title>
    </ion-card-header>

    <ion-card-content>
      <ion-card-subtitle>{{ confirmation.booking.roomName}}</ion-card-subtitle>
      <p v-if="confirmation.booking.roomDescription" class="description">{{confirmation.booking.roomDescription}}</p>
      <img :src="getRoomImageUrl(confirmation.booking.roomTypeId)" :alt="confirmation.booking.roomName"/>

      <ion-label>Amenities</ion-label>
      <RoomExtrasList :extras="confirmation.booking.roomExtras" class="extras-section"/>
      <br>
      <h2>Booked For:</h2>
      <p><strong>Period: </strong>{{confirmation.booking.checkIn}} - {{confirmation.booking.checkOut}}</p>
      <p><strong>Name: </strong>{{confirmation.booking.firstName}} {{confirmation.booking.lastName}}</p>
      <p><strong>Email: </strong>{{confirmation.booking.email}}</p>
      <p><strong>Breakfast: </strong>{{confirmation.booking.breakfast? 'Yes' : 'No' }}</p>

      <h2>Directions</h2>
      <p>
        CopyCats Hotel is located at Palmoria Bay Street 1/2/3, easily accessible via public transport.
      </p>

      <p>
        Take Metro Line 2 towards Palmoria Bay Central and exit at “Palmoria Bay Waterfront”.
        From there, it’s a 5-minute walk straight down Palmoria Bay Street to the hotel entrance.
      </p>

      <h2>Contact</h2>
      <p><strong>Email: </strong>helpcopycathotel@mail.com</p>
      <p><strong>Support-Hotline: </strong>+00 (0) 0000000</p>

    </ion-card-content>
  </ion-card>


  <StatusCard
    variant="success"
    subtitle="Booking confirmed"
    :title="confirmation.title"
    :message="confirmation.message"
    :action-label="confirmation.actionLabel"
    :action-route="confirmation.actionRoute"
  />
  </div>
</template>

<script lang="ts">
import {computed, PropType} from 'vue'
import type {Room} from '@/models/Room'
import type { BookingConfirmation } from '@/models/BookingConfirmation'
import {getRoomImageUrl} from '@/models/Room'
import StatusCard from '@/components/common/StatusCard.vue'
import BookingSummaryCard from "@/components/booking/BookingSummaryCard.vue";
import Section from "@/components/common/section.vue";
import BannerSection from "@/components/common/banner.vue";
import RoomExtrasList from "@/components/room/RoomExtrasList.vue";

export default {
  name: 'BookingConfirmationCard',

  components: {
    RoomExtrasList,
    BannerSection,
    Section,
    BookingSummaryCard,
    StatusCard
  },

  props: {
    confirmation: {
      type: Object as PropType<BookingConfirmation>,
      required: true
    }
  },

  methods: {
    getRoomImageUrl
  }
}
</script>

<style>
.booking-card {
  margin: 0;
  border-radius: 16px;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
}

.description,
.summary-row {
  color: var(--ion-color-medium);
  line-height: 1.6;
}
</style>
