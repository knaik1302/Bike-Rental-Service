export type TResponse<T> = {
  data: T
  error: string | null
  message: string | null
  success: boolean
}

export type TArrayReponse<T> = {
  totalCount: number
  items: T[]
}
