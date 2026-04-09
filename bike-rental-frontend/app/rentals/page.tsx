"use client"

import PLPFilters from "@/components/PLPFilters"
import RentalCards from "@/components/RentalCards"
import { useGetBikes } from "@/query/hooks/bike.hook"

export default function RentalsPage() {
  const { data, isLoading, isError } = useGetBikes()
  if (isLoading) {
    return (
      <span className="flex items-center justify-center h-screen">
        <p className="font-bold text-2xl">Loading...</p>
      </span>
    )
  }
  if (isError) {
    return (
      <span className="flex items-center justify-center h-screen">
        <p className="font-bold text-2xl text-red-600">
          Something went wrong 😱!!!
        </p>
      </span>
    )
  }
  if (data && data.data.totalCount === 0) {
    return (
      <>
        <span className="flex items-center justify-center h-screen">
          <p className="font-bold text-2xl text-red-600">
            No bikes available... 🥲
          </p>
        </span>
      </>
    )
  }

  if (data && data !== undefined) {
    return (
      <PLPFilters>
        <div className="flex items-center justify-center flex-wrap gap-10">
          {data.data.items.map((i) => {
            return <RentalCards key={i.id} {...i} />
          })}
        </div>
      </PLPFilters>
    )
  }
}
