package com.apress.springbootrecipes.order;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.TEN;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  @Test
  public void ordersEventStream() throws Exception {

    List<Order> orders = List.of(new Order("1234", TEN));
    when(orderService.findAll()).thenReturn(orders);

    MvcResult mvcResult = mockMvc.perform(get("/orders"))
      .andExpect(request().asyncStarted())
      .andDo(MockMvcResultHandlers.log())
      .andReturn();

    mockMvc.perform(asyncDispatch(mvcResult))
      .andDo(MockMvcResultHandlers.log())
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(TEXT_EVENT_STREAM_VALUE))
      .andExpect(content().string(
        allOf(
          containsString("data:{\"id\":\"1234\",\"amount\":10}"),
          containsString("event:order-created"),
          containsString("id:"))));
  }
}
