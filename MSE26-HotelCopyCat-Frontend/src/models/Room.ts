export interface Room {
    id: number
    title: string
    description: string
    imagePath: string
    extras: RoomExtra[]
}

export interface RoomExtra {
    id: number
    name: string
    iconPath: string
}