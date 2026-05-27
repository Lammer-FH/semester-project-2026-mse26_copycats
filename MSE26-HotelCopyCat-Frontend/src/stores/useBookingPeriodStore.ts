import { defineStore } from 'pinia'

interface BookingPeriod {
  startDate: string
  endDate: string
}

export const useBookingStore = defineStore('booking', {
  state: (): BookingPeriod => ({
    startDate: '',
    endDate: ''
  }),

  getters: {
    isValidPeriod: (state) => {
      return (
        state.startDate !== '' &&
        state.endDate !== '' &&
        new Date(state.startDate) <= new Date(state.endDate)
      )
    },

    durationInDays: (state) => {
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
      this.startDate = ''
      this.endDate = ''
    }
  }
})