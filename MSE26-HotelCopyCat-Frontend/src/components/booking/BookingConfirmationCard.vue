<template>
  <StatusCard
    variant="success"
    subtitle="Booking confirmed"
    :title="confirmation.title"
    :message="confirmation.message"
    :action-label="confirmation.actionLabel"
    :action-route="confirmation.actionRoute"
  />
</template>

<script lang="ts">
import type { PropType } from 'vue'
import type { BookingConfirmation } from '@/models/BookingConfirmation'
import StatusCard from '@/components/common/StatusCard.vue'

export default {
  name: 'BookingConfirmationCard',

  components: {
    StatusCard
  },

  props: {
    confirmation: {
      type: Object as PropType<BookingConfirmation>,
      required: true
    }
  }
}
</script>
