"use client"
import { Button } from "@/components/ui/button"
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { useLogin } from "@/query/hooks/auth.hooks"
import { useState } from "react"

export default function TabsDemo() {
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")
  const { mutate, isPending } = useLogin()
  return (
    <div className="w-full flex items-center justify-center p-8">
      <div className="flex flex-col w-full max-w-sm gap-6 ">
        <Tabs defaultValue="login">
          <TabsList>
            <TabsTrigger value="login">Login</TabsTrigger>
            <TabsTrigger value="signup">Signup</TabsTrigger>
          </TabsList>
          <TabsContent value="signup">
            <Card>
              <CardHeader>
                <CardTitle>Signup</CardTitle>
                <CardDescription>Already signup? Use login.</CardDescription>
              </CardHeader>
              <CardContent className="grid gap-6">
                <div className="grid gap-3">
                  <Label htmlFor="username">Email</Label>
                  <Input id="username" placeholder="Enter your email id" />
                </div>
                <div className="grid gap-3">
                  <Label htmlFor="password">Password</Label>
                  <Input id="password" placeholder="Password" type="password" />
                </div>
              </CardContent>
              <CardFooter>
                <Button>Signup</Button>
              </CardFooter>
            </Card>
          </TabsContent>
          <TabsContent value="login">
            <Card>
              <CardHeader>
                <CardTitle>Login</CardTitle>
                <CardDescription>New User? Click on signup.</CardDescription>
              </CardHeader>
              <CardContent className="grid gap-6">
                <div className="grid gap-3">
                  <Label htmlFor="useremail">Email</Label>
                  <Input
                    id="useremail"
                    type="text"
                    placeholder="Enter your email id"
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                <div className="grid gap-3">
                  <Label htmlFor="userpassword">Password</Label>
                  <Input
                    id="userpassword"
                    type="password"
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
              </CardContent>
              <CardFooter>
                <Button
                  disabled={isPending}
                  onClick={() => {
                    mutate({
                      email,
                      password,
                    })
                  }}
                >
                  Login
                </Button>
              </CardFooter>
            </Card>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  )
}
