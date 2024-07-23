import RecipeInformation from "@/components/RecipeInformation";

interface PageProps {
  params: { 
    id: string 
  }
}

export default function Page({ params }: PageProps) {
  return <RecipeInformation id={params.id} />;
}
