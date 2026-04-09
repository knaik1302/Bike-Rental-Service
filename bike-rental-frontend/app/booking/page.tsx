"use client"

import { Button } from "@/components/ui/button"
import { Calendar } from "@/components/ui/calendar"
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { useCreateBooking } from "@/query/hooks/booking.hook"
import { useBikeId } from "@/store/common.store"
import { useState } from "react"
import { DateRange } from "react-day-picker"

const Booking = () => {
  const { mutate, isPending } = useCreateBooking()
  const { bikeSelected } = useBikeId()

  const [range, setRange] = useState<DateRange | undefined>({
    from: new Date(),
    to: new Date(new Date().setDate(new Date().getDate() + 5)),
  })

  return (
    <div className="flex flex-col items-center justify-center">
      <Card className="m-8">
        <CardHeader>
          <CardTitle>Booking</CardTitle>
          <CardDescription>Choose the date range</CardDescription>
        </CardHeader>
        <CardContent>
          <Calendar
            mode="range"
            defaultMonth={new Date()}
            selected={range}
            onSelect={setRange}
            numberOfMonths={2}
            disabled={{ before: new Date() }}
          />
        </CardContent>
      </Card>

      <Button
        disabled={isPending || !range?.from || !range?.to}
        onClick={() =>
          mutate({
            bikeId: bikeSelected!,
            startTime: range!.from!.toISOString().split(".")[0],
            endTime: range!.to!.toISOString().split(".")[0],
          })
        }
      >
        Book now
      </Button>
    </div>
  )
}

export default Booking
