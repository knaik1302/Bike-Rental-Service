import { create } from "zustand"
import { combine, persist } from "zustand/middleware"
import { useShallow } from "zustand/shallow"
export const useCommonStore = create(
  persist(
    combine(
      { token: null as null | string, bikeSelected: null as null | number },

      (set) => ({
        setToken: (token: string | null) => set({ token: token }),
        setBikeSelected: (id: number | null) => set({ bikeSelected: id }),
      })
    ),
    { name: "common-store" }
  )
)

export const useToken = () =>
  useCommonStore(useShallow(({ setToken, token }) => ({ setToken, token })))

export const useBikeId = () =>
  useCommonStore(
    useShallow(({ bikeSelected, setBikeSelected }) => ({
      bikeSelected,
      setBikeSelected,
    }))
  )
