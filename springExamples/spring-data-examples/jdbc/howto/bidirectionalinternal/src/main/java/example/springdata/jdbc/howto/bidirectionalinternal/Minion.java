/*
 * Copyright 2021 the original author or authors.
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
package example.springdata.jdbc.howto.bidirectionalinternal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

class Minion {
	@Id
	Long id;
	String name;
	final Set<Toy> toys = new HashSet<>();

	Minion(String name) {
		this.name = name;
	}

	@PersistenceCreator
	private Minion(Long id, String name, Collection<Toy> toys) {

		this.id = id;
		this.name = name;
		toys.forEach(this::addToy);
	}

	public void addToy(Toy toy) {
		toys.add(toy);
		toy.minion = this;
	}

	public void showYourToys() {
		toys.forEach(Toy::sayHello);
	}
}
