import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.vertexai.VertexAiImageModel;

import java.nio.file.Path;

public class AInktober {
    public static void main(String[] args) {
        VertexAiImageModel imagenModel = VertexAiImageModel.builder()
            .endpoint(System.getenv("GCP_VERTEXAI_ENDPOINT"))
            .location(System.getenv("GCP_LOCATION"))
            .project(System.getenv("GCP_PROJECT_ID"))
            .publisher("google")
            .modelName("imagen-3.0-fast-generate-001")
            .aspectRatio(VertexAiImageModel.AspectRatio.SQUARE)
            .negativePrompt("watercolor, gray shades")
            .persistTo(Path.of("target/"))
            .build();

        String prompt = """
            A black and white ink drawing of a
            backpack, on a fully white background
            """;

        Response<Image> imageResponse = imagenModel.generate(prompt);
        System.out.println(imageResponse.content().url());
    }
}
