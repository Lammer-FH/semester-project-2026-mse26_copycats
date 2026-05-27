export interface Room {
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