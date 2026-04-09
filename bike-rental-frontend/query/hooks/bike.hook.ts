import { useQuery } from "@tanstack/react-query"
import { getBikes } from "../functions/bike.fn"

export const useGetBikes = () =>
  useQuery({
    queryKey: ["bike"],
    queryFn: getBikes,
  })
