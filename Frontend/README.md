# Cartly Frontend

Cartly is the frontend for an e-commerce website. This project provides the user interface for customer shopping flows and admin management flows, including authentication, product browsing, cart management, checkout, orders, search, and product administration.

## Project Overview

This application is built as a single-page app using React and Vite. It includes separate routes for:

- Customer shopping pages
- Admin dashboard and product management
- Login and registration
- Cart and checkout
- Order history and payment flow
- Product search and filtering

## Technologies Used

- React
- Vite
- React Router DOM
- Redux Toolkit
- React Redux
- Tailwind CSS
- Radix UI
- Axios
- ESLint

## Project Structure

```text
src/
  components/      Reusable UI and feature components
  pages/           Route-level pages for auth, admin, and shopping views
  store/           Redux store and slices
  assets/          Images and static assets
  config/          Form and UI configuration
```

## Prerequisites

Make sure you have the following installed:

- Node.js
- npm

## Installation

Install dependencies:

```bash
npm install
```

## How to Run

Start the development server:

```bash
npm run dev
```

Then open the local URL shown by Vite in your browser.

## Available Scripts

```bash
npm run dev      # Start the development server
npm run build    # Build the project for production
npm run preview  # Preview the production build
npm run lint     # Run ESLint
```

## Backend Requirement

This frontend currently makes API requests to:

```text
http://localhost:5000
```

Make sure the backend server for Cartly is running on port `5000`; otherwise authentication, products, cart, checkout, and order features will not work.

## Main Features

- User registration and login
- Admin product management
- Product listing and filtering
- Search functionality
- Shopping cart management
- Address management
- Checkout and payment flow
- Order tracking and order details

## Build for Production

To create a production build:

```bash
npm run build
```

To preview the production build locally:

```bash
npm run preview
```
