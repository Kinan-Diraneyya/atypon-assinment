import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Header from "@/components/Header";
import Ad from "@/components/Ad";
import Footer from "@/components/Footer";
import styles from "./layout.module.scss";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Spoonacular Recipe Client",
  description: "A client that fetches and views recipes using Spoonacular",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className={styles.content}>
          <Header />
          <main className={styles.main} role="main">
            {children}
          </main>
          <Ad />
          <Footer />
        </div>
      </body>
    </html>
  );
}
