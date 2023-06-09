/*
 * Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.jpa.multipleds.order;

import example.springdata.jpa.multipleds.customer.Customer.CustomerId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.util.Assert;

/**
 * Simple domain class representing an {@link Order}
 *
 * @author Oliver Gierke
 */
@Entity
@EqualsAndHashCode(of = "id")
@Getter
@RequiredArgsConstructor
@ToString
// Needs to be explicitly named as Order is a reserved keyword
@Table(name = "SampleOrder")
public class Order {

	private @Id @GeneratedValue Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //
	private final List<LineItem> lineItems = new ArrayList<>();
	private final CustomerId customer;

	Order() {
		this.customer = null;
	}

	/**
	 * Adds a {@link LineItem} to the {@link Order}.
	 *
	 * @param lineItem must not be {@literal null}.
	 */
	public void add(LineItem lineItem) {

		Assert.notNull(lineItem, "Line item must not be null!");

		this.lineItems.add(lineItem);
	}

	@Entity
	@EqualsAndHashCode(of = "id")
	@Getter
	@RequiredArgsConstructor
	@ToString
	@Table(name = "LineItem")
	public static class LineItem {

		private @Id @GeneratedValue Long id;
		private final String description;
	}
}
