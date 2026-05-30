import { defineStore } from 'pinia'

interface BookingPeriod {
  startDate: string | null
  endDate: string | null
}

export const useBookingPeriodStore = defineStore('booking', {
  state: (): BookingPeriod => ({
    startDate: null,
    endDate: null
  }),

  getters: {
    isValidPeriod: (state) => {
      return (
        state.startDate !== null &&
        state.endDate !== null &&
        new Date(state.startDate) <= new Date(state.endDate)
      )
    },

    durationInDays: (state) => {
      if (state.startDate === null || state.endDate === null) {
        return 0
      }
      const start = new Date(state.startDate)
      const end = new Date(state.endDate)

      const diff = end.getTime() - start.getTime()

      return diff / (1000 * 60 * 60 * 24)
    }
  },

  actions: {
    setBookingPeriod(start: string, end: string) {
      this.startDate = start
      this.endDate = end
    },

    clearBookingPeriod() {
      this.startDate = null
      this.endDate = null
    }
  }
})