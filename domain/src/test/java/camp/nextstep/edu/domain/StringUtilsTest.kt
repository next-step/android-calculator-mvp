package camp.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class StringUtilsTest {

	@Test
	fun `정수 캐스팅 실패 테스트`() {
		// given
		val str = "string"

		// then
		assertThrows(
			CAST_INT_EXCEPTION,
			IllegalStateException::class.java
		) {
			// when
			str.toIntOrThrow()
		}
	}

	@Test
	fun `정수 캐스팅 성공 테스트`() {
		// given
		val str = "123"

		// when
		val int = str.toIntOrThrow()

		// then
		assertThat(int).isEqualTo(123)
	}
}