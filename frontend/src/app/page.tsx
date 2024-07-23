import FeaturedRecipe from "@/components/FeaturedRecipe";
import RecipesGrid from "@/components/RecipesGrid";
import Separator from "@/components/Separator";

interface PageProps {
  searchParams?: { [key: string]: string | string[] | undefined };
}

export default function Page({ searchParams }: PageProps) {
  return (
    <>
      <RecipesGrid query={searchParams?.q} />
      <Separator />
      <FeaturedRecipe />
    </>
  );
}
