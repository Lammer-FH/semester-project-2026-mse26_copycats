import axios from 'axios'
import api from './api'
import type { BookingRequest } from '@/models/BookingRequest'
import type { BookingResponse } from '@/models/BookingResponse'

function getSafeBackendMessage(message: unknown) {
  if (typeof message !== 'string') {
    return ''
  }

  const normalized = message.trim()

  if (!normalized || normalized.toLowerCase().includes('no static resource')) {
    return ''
  }

  return normalized
}

export default {
  async createBooking(
    request: BookingRequest
  ): Promise<BookingResponse> {
    try {
      const response = await api.post('/bookings', request)

      return {
        id: response.data.id,
        roomTypeId: response.data.roomType.id,
        roomName: response.data.roomType.name,
        roomDescription: response.data.roomType.description,
        roomExtras: response.data.roomType.extras,
        checkIn: response.data.checkIn,
        checkOut: response.data.checkOut,
        firstName: response.data.firstName,
        lastName: response.data.lastName,
        email: response.data.email,
        breakfast: response.data.breakfast,
        status: response.data.status,
        createdAt: response.data.createdAt
      }
    } catch (error) {
      if (axios.isAxiosError(error)) {
        if (!error.response) {
          throw new Error('The booking service is currently unavailable. Please try again in a moment.')
        }

        const backendMessage = getSafeBackendMessage(error.response.data?.message)

        switch (error.response.status) {
          case 400:
            throw new Error(backendMessage || 'Some booking details are invalid. Please check your entries.')
          case 404:
            throw new Error(backendMessage || 'Booking is not available yet. Please try again later.')
          case 409:
            throw new Error(backendMessage || 'This room is no longer available for the selected period.')
          default:
            throw new Error(backendMessage || 'The booking could not be completed. Please try again later.')
        }
      }

      throw error
    }
  }
}
