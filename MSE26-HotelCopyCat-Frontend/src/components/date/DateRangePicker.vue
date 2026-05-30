<template>
  <div class="range-picker-wrapper">
    <VueDatePicker
      v-model="dateRange"
      range
      :min-date="today"
      :enable-time-picker="false"
      placeholder="Check-in - Check-out"
      auto-apply
      :formats="{ input: 'dd MMM yyyy' }"
    />
  </div>
</template>

<script lang="ts">
import { VueDatePicker } from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import { useBookingPeriodStore } from '@/stores/useBookingPeriodStore'

function toIsoDate(date: Date): string {
  const y = date.getFullYear()
  const m = String(date.getMonth() + 1).padStart(2, '0')
  const d = String(date.getDate()).padStart(2, '0')
  return `${y}-${m}-${d}`
}

function fromIsoDate(str: string): Date {
  const [y, m, d] = str.split('-').map(Number)
  return new Date(y, m - 1, d)
}

export default {
  name: 'DateRangePicker',

  components: { VueDatePicker },

  data() {
    return {
      bookingStore: useBookingPeriodStore()
    }
  },

  computed: {
    today(): Date {
      return new Date()
    },

    dateRange: {
      get(): [Date, Date] | null {
        if (this.bookingStore.startDate && this.bookingStore.endDate) {
          return [fromIsoDate(this.bookingStore.startDate), fromIsoDate(this.bookingStore.endDate)]
        }
        return null
      },
      set(value: [Date, Date] | null) {
        if (value && value[0] && value[1]) {
          this.bookingStore.startDate = toIsoDate(value[0])
          this.bookingStore.endDate = toIsoDate(value[1])
        } else {
          this.bookingStore.clearBookingPeriod()
        }
      }
    }
  },

  methods: {}
}
</script>

<style scoped>
.range-picker-wrapper {
  width: 100%;
  max-width: 480px;
}
</style>
