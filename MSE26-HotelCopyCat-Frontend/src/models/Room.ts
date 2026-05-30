export interface Room {
    id: number
    name: string
    description: string
    imagePath: string
    extras: RoomExtra[]
    available?: boolean
}

export interface RoomExtra {
    id: number
    name: string
    iconPath: string
}