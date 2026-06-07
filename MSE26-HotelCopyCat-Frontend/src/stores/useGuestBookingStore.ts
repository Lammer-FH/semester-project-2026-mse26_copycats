import { defineStore } from 'pinia'
import bookingService from '@/services/bookingService'
import roomService from '@/services/roomService'
import type { BookingConfirmation } from '@/models/BookingConfirmation'
import type { BookingForm } from '@/models/BookingForm'
import type { BookingRequest } from '@/models/BookingRequest'
import type { BookingResponse } from '@/models/BookingResponse'
import type { BookingRoomContext } from '@/models/BookingRoomContext'
import { useBookingPeriodStore } from './useBookingPeriodStore'

type BookingStep = 'form' | 'review' | 'confirmation'
type BookingField = keyof BookingForm

function createEmptyForm(): BookingForm {
  return {
    firstName: '',
    lastName: '',
    email: '',
    confirmEmail: '',
    breakfast: false
  }
}

function createEmptyTouchedState(): Record<BookingField, boolean> {
  return {
    firstName: false,
    lastName: false,
    email: false,
    confirmEmail: false,
    breakfast: false
  }
}

function isValidEmail(email: string) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
}

function normalizeEmail(email: string) {
  return email.trim().toLowerCase()
}

export const useGuestBookingStore = defineStore('guestBooking', {
  state: () => ({
    form: createEmptyForm(),
    touched: createEmptyTouchedState(),
    errors: {} as Record<string, string>,
    submitError: '',
    submitting: false,
    step: 'form' as BookingStep,
    confirmation: null as BookingConfirmation | null,
    room: null as BookingRoomContext | null,
    loadingRoom: false,
    roomLoadError: ''
  }),

  getters: {
    hasRoom: (state) => state.room !== null
  },

  actions: {
    async loadBookingContext(roomId: number) {
      this.resetFlow()
      this.loadingRoom = true
      this.roomLoadError = ''

      try {
        const room = await roomService.getRoomTypeById(roomId)

        if (!room) {
          this.room = null
          this.roomLoadError = 'The selected room could not be loaded.'
          return
        }

        this.room = {
          id: room.id,
          name: room.name,
          description: room.description
        }
      } catch {
        this.room = null
        this.roomLoadError = 'The selected room could not be loaded. Please go back and try again.'
      } finally {
        this.loadingRoom = false
      }
    },

    updateField(field: BookingField, value: string | boolean) {
      this.form[field] = value as never
      this.submitError = ''

      if (this.touched[field] || this.errors[field]) {
        this.validateField(field)
      }
    },

    validateField(field: BookingField, markTouched = false) {
      if (markTouched) {
        this.touched[field] = true
      }

      switch (field) {
        case 'firstName':
          if (!this.form.firstName.trim()) {
            this.errors.firstName = 'First name is required.'
          } else {
            delete this.errors.firstName
          }
          break
        case 'lastName':
          if (!this.form.lastName.trim()) {
            this.errors.lastName = 'Last name is required.'
          } else {
            delete this.errors.lastName
          }
          break
        case 'email':
          if (!this.form.email.trim()) {
            this.errors.email = 'Email address is required.'
          } else if (!isValidEmail(this.form.email)) {
            this.errors.email = 'Please enter a valid email address.'
          } else {
            delete this.errors.email
          }

          if (this.touched.confirmEmail || this.form.confirmEmail.trim()) {
            this.validateField('confirmEmail')
          }
          break
        case 'confirmEmail':
          if (!this.form.confirmEmail.trim()) {
            this.errors.confirmEmail = 'Please confirm your email address.'
          } else if (normalizeEmail(this.form.confirmEmail) !== normalizeEmail(this.form.email)) {
            this.errors.confirmEmail = 'The email addresses do not match.'
          } else {
            delete this.errors.confirmEmail
          }
          break
        case 'breakfast':
          delete this.errors.breakfast
          break
      }
    },

    resetFlow() {
      this.form = createEmptyForm()
      this.touched = createEmptyTouchedState()
      this.errors = {}
      this.submitError = ''
      this.submitting = false
      this.step = 'form'
      this.confirmation = null
      this.room = null
      this.loadingRoom = false
      this.roomLoadError = ''
    },

    validateForm() {
      this.validateField('firstName', true)
      this.validateField('lastName', true)
      this.validateField('email', true)
      this.validateField('confirmEmail', true)

      return Object.keys(this.errors).length === 0
    },

    goToReview() {
      this.submitError = ''

      if (!this.validateForm()) {
        return false
      }

      this.step = 'review'
      return true
    },

    goToForm() {
      this.step = 'form'
    },

    hasSubmitPrerequisites() {
      const bookingPeriodStore = useBookingPeriodStore()

      if (!this.room || !bookingPeriodStore.startDate || !bookingPeriodStore.endDate) {
        this.submitError = 'Room and booking period are required before submitting.'
        return false
      }

      if (!bookingPeriodStore.isValidPeriod) {
        this.submitError = 'Please select a valid booking period before submitting.'
        return false
      }

      return true
    },

    hasValidSubmissionData() {
      if (!this.validateForm()) {
        this.step = 'form'
        return false
      }

      return true
    },

    createBookingPeriodPayload() {
      const bookingPeriodStore = useBookingPeriodStore()

      return {
        checkIn: bookingPeriodStore.startDate as string,
        checkOut: bookingPeriodStore.endDate as string
      }
    },

    createBookingRequest(): BookingRequest {
      const period = this.createBookingPeriodPayload()

      return {
        roomTypeId: this.getRequiredRoom().id,
        checkIn: period.checkIn,
        checkOut: period.checkOut,
        firstName: this.form.firstName,
        lastName: this.form.lastName,
        email: normalizeEmail(this.form.email),
        confirmEmail: normalizeEmail(this.form.confirmEmail),
        breakfast: this.form.breakfast
      }
    },

    getRequiredRoom() {
      return this.room as BookingRoomContext
    },

    startSubmitting() {
      this.submitError = ''
      this.submitting = true
    },

    finishSubmitting() {
      this.submitting = false
    },

    createConfirmationCardModel(_response: BookingResponse): BookingConfirmation {
      // TODO update for U5
      return {
        title: 'Booking submitted',
        message: 'Your booking request was submitted successfully.',
        actionLabel: 'Back to rooms',
        actionRoute: '/rooms',
        booking: _response
      }
    },

    handleSubmitSuccess(response: BookingResponse) {
      this.confirmation = this.createConfirmationCardModel(response)
      this.step = 'confirmation'
      return true
    },

    handleSubmitError(error: unknown) {
      this.submitError = error instanceof Error
        ? error.message
        : 'Booking could not be completed. Please try again.'
      return false
    },

    async submitBooking() {
      if (!this.hasSubmitPrerequisites()) {
        return false
      }

      if (!this.hasValidSubmissionData()) {
        return false
      }

      this.startSubmitting()

      try {
        const response = await bookingService.createBooking(
          this.createBookingRequest()
        )

        return this.handleSubmitSuccess(response)
      } catch (error) {
        return this.handleSubmitError(error)
      } finally {
        this.finishSubmitting()
      }
    }
  }
})
