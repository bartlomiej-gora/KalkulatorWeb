package pl.bgora.kalkulatorweb

import com.github.bgora.rpnlibrary.Calculator
import io.javalin.plugin.openapi.annotations.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculatorController {

    @OpenApi(
        summary = "Gets value of the calculation",
        operationId = "calculate",
        tags = ["calculate"],
        pathParams = [OpenApiParam("input", String::class)],
        responses = [OpenApiResponse("200", [OpenApiContent(Array<Response>::class)])],
        path = "calculate",
        method = HttpMethod.GET
    )
    @GetMapping("/calculate/{input}")
    fun calculate(@PathVariable input: String): Response {
        val calculator = Calculator.createCalculator();
        val result = calculator.calculate(replaceDiv(input)).toString()
        return Response(input, result);
    }
}