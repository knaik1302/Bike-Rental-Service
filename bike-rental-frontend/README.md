## Getting Started

### 1. Install pnpm
```bash
npm install -g pnpm
```
### 2. Environment Setup
Create a `.env.local` file in the root directory:
```env
NEXT_PUBLIC_API_BASE_URL="http://localhost:8080"
```

### 3. Installation
```bash
pnpm install
```

### 4. Run the Application
Development Mode:
```bash
pnpm dev
```
Production Build & Run:
```Bash
pnpm build && pnpm start
```

### Available Scripts
```Bash
# Run development server
pnpm dev

# Build for production
pnpm build

# Start production server
pnpm start

# Run linter
pnpm lint
```
