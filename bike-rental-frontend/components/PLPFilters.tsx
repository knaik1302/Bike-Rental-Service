import { FilterIcon } from "lucide-react"
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "./ui/accordion"
import { Checkbox } from "./ui/checkbox"
import { Label } from "./ui/label"

const PLPFilters = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="flex my-4">
      <div className="hidden md:block md:min-w-[20%] pl-4">
        <p className="text-3xl font-bold text-left hidden md:flex md:items-center md:justify-start gap-2">
          Filters <FilterIcon fill="#000" />
        </p>
        <Accordion type="single" collapsible>
          {Array.from({ length: 2 }).map((i, k) => {
            return (
              <AccordionItem
                value={"value" + k}
                key={k}
                className="border-b border-border"
              >
                <AccordionTrigger className="py-2 text-md font-medium  text-muted-foreground hover:no-underline">
                  Vehicle Type
                </AccordionTrigger>
                <AccordionContent className="flex flex-col justify-center items-start gap-2 bg-slate-50 p-2">
                  <div className="flex gap-2 items-center justify-center">
                    <Checkbox id="scooter" />
                    <Label htmlFor="scooter">Bike</Label>
                  </div>
                  <div className="flex gap-2 items-center justify-center">
                    <Checkbox id="scooter" />
                    <Label htmlFor="scooter">Scooter</Label>
                  </div>
                </AccordionContent>
              </AccordionItem>
            )
          })}
        </Accordion>
      </div>
      <div className="bg-gray-50 p-2">{children}</div>
    </div>
  )
}
export default PLPFilters
