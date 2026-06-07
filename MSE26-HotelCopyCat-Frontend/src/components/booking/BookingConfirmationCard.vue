<template>


  <StatusCard
      variant="success"
      subtitle="Booking confirmed"
      :title="confirmation.title"
      :message="confirmation.message"
      :action-label="confirmation.actionLabel"
      :action-route="confirmation.actionRoute"
  >
    <template #bookingDetails>
      <div class="container">
        <ion-card-title>Your booking</ion-card-title>

        <ion-card-subtitle>{{ confirmation.booking.roomName }}</ion-card-subtitle>
        <p v-if="confirmation.booking.roomDescription" class="description">
          {{ confirmation.booking.roomDescription }}</p>
        <img :src="getRoomImageUrl(confirmation.booking.roomTypeId)" class="room-image"
             :alt="confirmation.booking.roomName"/>

        <h1>Amenities</h1>
        <RoomExtrasList :extras="confirmation.booking.roomExtras" class="extras-section"/>
        <br>
        <h1>Booking Details</h1>
        <p><strong>Period: </strong>{{ confirmation.booking.checkIn }} - {{ confirmation.booking.checkOut }}</p>
        <p><strong>Name: </strong>{{ confirmation.booking.firstName }} {{ confirmation.booking.lastName }}</p>
        <p><strong>Email: </strong>{{ confirmation.booking.email }}</p>
        <p><strong>Breakfast: </strong>{{ confirmation.booking.breakfast ? 'Yes' : 'No' }}</p>

        <h1>Directions</h1>
        <p>
          CopyCats Hotel is located at Palmoria Bay Street 1/2/3, easily accessible via public transport.
        </p>

        <p>
          Take Metro Line 2 towards Palmoria Bay Central and exit at “Palmoria Bay Waterfront”.
          From there, it’s a 5-minute walk straight down Palmoria Bay Street to the hotel entrance.
        </p>

        <h1>Contact</h1>
        <p><strong>Email: </strong>helpcopycathotel@mail.com</p>
        <p><strong>Support-Hotline: </strong>+00 (0) 0000000</p>

      </div>
    </template>
  </StatusCard>


</template>

<script lang="ts">
import {computed, PropType} from 'vue'
import type {Room} from '@/models/Room'
import type {BookingConfirmation} from '@/models/BookingConfirmation'
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
.container {
  margin-top: 1rem;
  line-height: 1.6;
}

.container p {
  line-height: 1.5;
  font-size: 1.2rem;
  padding: 0.2rem 0 0.2rem 0;
}

.container h1 {
  line-height: 1.5;
  font-size: 1.5rem;
  color: var(--ion-color-dark);
  padding: 0.5rem 0 0.3rem 0;
  font-weight: bold;
}

.room-image {
  width: 100%;
  max-height: 40rem;
  object-fit: cover;
  border-radius: 14px;
}

</style>