import type { RoomExtra } from '@/models/Room'

export interface BookingResponse {
  id: number
  roomTypeId: number
  roomName: string
  roomDescription: string
  roomExtras: RoomExtra[]
  checkIn: string
  checkOut: string
  firstName: string
  lastName: string
  email: string
  breakfast: boolean
  status: string
  createdAt: string
}
