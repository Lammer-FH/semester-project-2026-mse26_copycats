import api from './api'

export default {

  async getRoomTypes(page = 0, size = 5) {
    const response = await api.get('/room-types', {
      params: { page, size }
    })
    return {
      rooms: response.data.content,
      page: response.data.page.number,
      size: response.data.page.size,
      totalElements: response.data.page.totalElements,
      totalPages: response.data.page.totalPages
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