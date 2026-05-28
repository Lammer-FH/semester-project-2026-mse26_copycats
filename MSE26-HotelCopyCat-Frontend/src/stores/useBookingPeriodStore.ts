import { defineStore } from 'pinia'

interface BookingPeriod {
  startDate: string | undefined
  endDate: string | undefined
}

export const useBookingPeriodStore = defineStore('booking', {
  state: (): BookingPeriod => ({
    startDate: undefined,
    endDate: undefined
  }),

  getters: {
    isValidPeriod: (state) => {
      return (
        state.startDate !== undefined &&
        state.endDate !== undefined &&
        new Date(state.startDate) <= new Date(state.endDate)
      )
    },

    durationInDays: (state) => {
      if (state.startDate === undefined || state.endDate === undefined) {
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
      this.startDate = undefined
      this.endDate = undefined
    }
  }
})