export interface Room {
    id: number
    name: string
    description: string
    extras: RoomExtra[]
    available?: boolean
}

export function getRoomImageUrl(id: number): string {
    return new URL(`../images/rooms/${id}.jpg`, import.meta.url).href
}

export interface RoomExtra {
    id: number
    name: string
    iconPath: string
}