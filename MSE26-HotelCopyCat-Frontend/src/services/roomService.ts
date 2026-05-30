import api from './api'

import spa from '@/images/icons/spa.png'
import minibar from '@/images/icons/bar.png'
import wifi from '@/images/icons/wifi.png'

export default {

  async getRoomTypes(page = 0, size = 5) {
    // const response = await api.get('/room-types', {
    //   params: {
    //     page,
    //     size
    //   }
    // })

    //return response.data
    // TODO: remove static data once rooms are fetched from api
    const allRooms = [{
          id: 0,
          name: 'Classic Double',
          description: 'Cozy room with double bed',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi }
          ]
        },
        {
          id: 1,
          name: 'Classic Double Sea View',
          description: 'Cozy room with double bed and sea view',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 2,
          name: 'Classic King',
          description: 'Spacious room with king bed',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 3,
          name: 'Classic King Sea View',
          description: 'Spacious room with king bed and sea view',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 4,
          name: 'Suite Double',
          description: 'Luxury suite with double bed',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 5,
          name: 'Suite Double Sea View',
          description: 'Luxury suite with double bed and sea view',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 6,
          name: 'Suite King',
          description: 'Luxury suite with king bed',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 3, name: 'Spa', iconPath: spa }
          ]
        },
        {
          id: 7,
          name: 'Suite King Sea View',
          description: 'Luxury suite with king bed and sea view',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 3, name: 'Spa', iconPath: spa }
          ]
        },
        {
          id: 8,
          name: 'Presidential Suite',
          description: 'Ultimate Presidential Suite with king bed',
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 3, name: 'Spa', iconPath: spa }
          ]
        }]
    const start = page * size
    const end = start + size

    const paginatedRooms = allRooms.slice(start, end)
    return {
        rooms: paginatedRooms,
        page: page,
        size: size,
        totalElements: allRooms.length,
        totalPages: Math.ceil(allRooms.length / size)
    }
    
  },

  async getRoomTypeById(id: number) {
    const response = await api.get(`/room-types/${id}`)
    return response.data
  },

  async getAvailability(
    roomTypeId: number,
    checkIn: string | null,
    checkOut: string | null
  ) {

    // const response = await api.get(
    //   `/room-types/${roomTypeId}/availability`,
    //   {
    //     params: {
    //       checkIn,
    //       checkOut
    //     }
    //   }
    // )

    //return response.data
    // TODO: remove static availability once real data is fetched from api
    return roomTypeId < 5;
  }
}