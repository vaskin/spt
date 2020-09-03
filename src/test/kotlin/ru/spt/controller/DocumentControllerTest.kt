package ru.spt.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class DocumentControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `create document success`() {
        mockMvc.post("/documents") {
            content = """
                {
                   "seller":"1235342511111",
                   "customer":"6485635242222",
                   "products":[
                      {
                         "name":"milk",
                         "code":"2364758363546"
                      },
                      {
                         "name":"water",
                         "code":"3656352437590"
                      }
                   ]
                }
            """.trimIndent()
            contentType = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status { isOk }
        }
    }

    @Test
    fun `missing required parameter`() {
        mockMvc.post("/documents") {
            content = """
                {
                   "customer":"648563524",
                   "products":[
                      {
                         "name":"milk",
                         "code":"2364758363546"
                      },
                      {
                         "name":"water",
                         "code":"3656352437590"
                      }
                   ]
                }
            """.trimIndent()
            contentType = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status { is4xxClientError }
        }
    }

    @Test
    fun `size not valid`() {
        mockMvc.post("/documents") {
            content = """
                {
                   "seller":"1",
                   "customer":"6485635242222",
                   "products":[
                      {
                         "name":"milk",
                         "code":"2364758363546"
                      },
                      {
                         "name":"water",
                         "code":"3656352437590"
                      }
                   ]
                }
            """.trimIndent()
            contentType = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status { is4xxClientError }
        }
    }
}