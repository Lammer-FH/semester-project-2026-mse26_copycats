import api from './api'
import classicDouble from '@/assets/rooms/classic-double.jpg'
import classicKing from '@/assets/rooms/classic-king.jpg'
import suiteDouble from '@/assets/rooms/suite-double.jpg'
import suiteKing from '@/assets/rooms/suite-king.jpg'
import presidentialSuite from '@/assets/rooms/presidential-suite.jpg'

import spa from '@/assets/icons/spa.png'
import minibar from '@/assets/icons/bar.png'
import wifi from '@/assets/icons/wifi.png'

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
          title: 'Classic Double',
          description: 'Cozy room with double bed',
          imagePath: classicDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi }
          ]
        },
        {
          id: 1,
          title: 'Classic Double Sea View',
          description: 'Cozy room with double bed and sea view',
          imagePath: classicDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 2,
          title: 'Classic King',
          description: 'Spacious room with king bed',
          imagePath: classicKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 3,
          title: 'Classic King Sea View',
          description: 'Spacious room with king bed and sea view',
          imagePath: classicKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 4,
          title: 'Suite Double',
          description: 'Luxury suite with double bed',
          imagePath: suiteDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 5,
          title: 'Suite Double Sea View',
          description: 'Luxury suite with double bed and sea view',
          imagePath: suiteDouble,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar }
          ]
        },
        {
          id: 6,
          title: 'Suite King',
          description: 'Luxury suite with king bed',
          imagePath: suiteKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 2, name: 'Spa', iconPath: spa }
          ]
        },
        {
          id: 7,
          title: 'Suite King Sea View',
          description: 'Luxury suite with king bed and sea view',
          imagePath: suiteKing,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 2, name: 'Spa', iconPath: spa }
          ]
        },
        {
          id: 8,
          title: 'Presidential Suite',
          description: 'Ultimate Presidential Suite with king bed',
          imagePath: presidentialSuite,
          extras: [
            { id: 1, name: 'WiFi', iconPath: wifi },
            { id: 2, name: 'Minibar', iconPath: minibar },
            { id: 2, name: 'Spa', iconPath: spa }
          ]
        }]
    const start = page * size
    const end = start + size

    const paginatedRooms = allRooms.slice(start, end)
    return {
        rooms: paginatedRooms,
        page: page,
        size: size,
        totalElements: 9,
        totalPages: 2
    }
    
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