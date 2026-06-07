<template>
  <StatusCard
    class="booking-confirmation-card"
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
          {{ confirmation.booking.roomDescription }}
        </p>
        <img
          :src="getRoomImageUrl(confirmation.booking.roomTypeId)"
          class="room-image"
          :alt="confirmation.booking.roomName"
        />

        <h1>Amenities</h1>
        <RoomExtrasList :extras="confirmation.booking.roomExtras" class="extras-section" />
        <br />

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
          Take Metro Line 2 towards Palmoria Bay Central and exit at "Palmoria Bay Waterfront".
          From there, it's a 5-minute walk straight down Palmoria Bay Street to the hotel entrance.
        </p>

        <h1>Contact</h1>
        <p><strong>Email: </strong>helpcopycathotel@mail.com</p>
        <p><strong>Support-Hotline: </strong>+00 (0) 0000000</p>
      </div>
    </template>
  </StatusCard>
</template>

<script lang="ts">
import { PropType } from 'vue'
import type { BookingConfirmation } from '@/models/BookingConfirmation'
import { getRoomImageUrl } from '@/models/Room'
import StatusCard from '@/components/common/StatusCard.vue'
import RoomExtrasList from '@/components/room/RoomExtrasList.vue'

export default {
  name: 'BookingConfirmationCard',

  components: {
    RoomExtrasList,
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
  padding: 0.2rem 0;
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

@media print {
  @page {
    size: A4 portrait;
    margin: 12mm;
  }

  html,
  body {
    margin: 0 !important;
    padding: 0 !important;
    background: #fff !important;
  }

  .booking-page-shell ion-header,
  .booking-page-shell .site-footer {
    display: none !important;
  }

  .booking-page-shell ion-content {
    --overflow: visible;
    height: auto;
  }

  .booking-page-shell ion-content::part(scroll) {
    overflow: visible !important;
  }

  .booking-page-shell ion-grid,
  .booking-page-shell .content-container,
  .booking-page-shell .booking-page {
    margin: 0 !important;
    padding: 0 !important;
    max-width: none !important;
    width: 100% !important;
  }

  .booking-confirmation-card {
    box-shadow: none !important;
    border: 1px solid #d9d9d9 !important;
    border-radius: 0 !important;
    break-inside: avoid;
    -webkit-print-color-adjust: exact;
    print-color-adjust: exact;
  }

  .booking-confirmation-card .action-button {
    display: none !important;
  }

  .booking-confirmation-card .container {
    margin-top: 0;
    line-height: 1.45;
    font-size: 10.5pt;
  }

  .booking-confirmation-card .container p {
    font-size: 10pt;
    line-height: 1.4;
  }

  .booking-confirmation-card .container h1 {
    font-size: 13pt;
    break-after: avoid;
  }

  .booking-confirmation-card .room-image {
    max-height: 60mm;
    border-radius: 8px;
  }

  .booking-confirmation-card .extras-section {
    gap: 6px;
  }

  .booking-confirmation-card .extra-item {
    gap: 4px;
  }

  .booking-confirmation-card .extra-icon {
    width: 16px;
    height: 16px;
    filter: none !important;
  }
}
</style>
