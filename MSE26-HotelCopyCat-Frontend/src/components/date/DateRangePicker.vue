<template>
  <div class="range-picker-wrapper">
    <VueDatePicker
      :model-value="localRange"
      @update:model-value="onUpdate"
      range
      :min-date="today"
      :enable-time-picker="false"
      placeholder="Check-in - Check-out"
      auto-apply
      :formats="{ input: 'dd MMM yyyy' }"
      :teleport="false"
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
      bookingStore: useBookingPeriodStore(),
      localRange: null as [Date, Date] | null
    }
  },

  created() {
    if (this.bookingStore.startDate && this.bookingStore.endDate) {
      this.localRange = [fromIsoDate(this.bookingStore.startDate), fromIsoDate(this.bookingStore.endDate)]
    }
  },

  computed: {
    today(): Date {
      return new Date()
    }
  },

  methods: {
    onUpdate(value: [Date, Date] | null) {
      this.localRange = value
      if (value && value[0] && value[1]) {
        this.bookingStore.setBookingPeriod(toIsoDate(value[0]), toIsoDate(value[1]))
      } else if (!value) {
        this.bookingStore.clearBookingPeriod()
      }
    }
  }
}
</script>

<style scoped>
.range-picker-wrapper {
  width: 100%;
  max-width: 480px;
  position: relative;
}

.range-picker-wrapper :deep(.dp--input) {
  background-color: var(--ion-item-background, #fff);
  color: var(--ion-text-color, #212121);
  border: 1.5px solid var(--ion-color-step-200, #c8c7cc);
  border-radius: 10px;
  height: 44px;
  font-family: var(--ion-font-family, system-ui, -apple-system, sans-serif);
  font-size: 0.9375rem;
}

.range-picker-wrapper :deep(.dp--input::placeholder) {
  color: var(--ion-color-medium, #92949c);
  opacity: 1;
}

.range-picker-wrapper :deep(.dp--input:hover:not(.dp--input-focus)) {
  border-color: var(--ion-color-step-350, #999);
}

.range-picker-wrapper :deep(.dp--input-focus) {
  border-color: var(--ion-color-primary, #3880ff);
  box-shadow: 0 0 0 3px rgba(var(--ion-color-primary-rgb, 56, 128, 255), 0.15);
}

.range-picker-wrapper :deep(.dp--input-icon),
.range-picker-wrapper :deep(.dp--clear-btn) {
  color: var(--ion-color-medium, #92949c);
}

@media (prefers-color-scheme: dark) {
  .range-picker-wrapper :deep(.dp--input) {
    background-color: var(--ion-item-background, #1c1c1e);
    border-color: var(--ion-color-step-300, #4d4d4d);
  }

  .range-picker-wrapper :deep(.dp--overlay-cell-disabled),
  .range-picker-wrapper :deep(.dp--overlay-cell-disabled:hover) {
    background: rgba(255, 255, 255, 0.07);
    color: #555;
  }
}

.range-picker-wrapper :deep(.dp--menu) {
  background: var(--ion-card-background, var(--ion-background-color, #fff));
  border: 1px solid var(--ion-color-step-150, #e0e0e0);
  border-radius: 16px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.14);

  --dp-background-color: var(--ion-card-background, var(--ion-background-color, #fff));
  --dp-text-color: var(--ion-text-color, #212121);
  --dp-hover-color: var(--ion-color-step-100, #f3f3f3);
  --dp-hover-text-color: var(--ion-text-color, #212121);
  --dp-hover-icon-color: var(--ion-color-medium, #92949c);
  --dp-primary-color: var(--ion-color-primary, #3880ff);
  --dp-primary-disabled-color: var(--ion-color-primary-tint, #6bacea);
  --dp-primary-text-color: var(--ion-color-primary-contrast, #fff);
  --dp-secondary-color: var(--ion-color-step-300, #c0c4cc);
  --dp-border-color: var(--ion-color-step-150, #e0e0e0);
  --dp-menu-border-color: var(--ion-color-step-150, #e0e0e0);
  --dp-border-color-hover: var(--ion-color-primary, #3880ff);
  --dp-border-color-focus: var(--ion-color-primary, #3880ff);
  --dp-disabled-color: var(--ion-color-step-50, #f6f6f6);
  --dp-disabled-color-text: var(--ion-color-step-400, #8e8e8e);
  --dp-icon-color: var(--ion-color-medium, #92949c);
  --dp-range-between-dates-background-color: rgba(var(--ion-color-primary-rgb, 56, 128, 255), 0.12);
  --dp-range-between-dates-text-color: var(--ion-text-color, #212121);
  --dp-range-between-border-color: rgba(var(--ion-color-primary-rgb, 56, 128, 255), 0.12);
  --dp-highlight-color: rgba(var(--ion-color-primary-rgb, 56, 128, 255), 0.1);
  --dp-font-family: var(--ion-font-family, system-ui, -apple-system, sans-serif);
  --dp-cell-border-radius: 8px;
  --dp-border-radius: 10px;
}
</style>
