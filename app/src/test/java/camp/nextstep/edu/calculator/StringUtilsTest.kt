package camp.nextstep.edu.calculator

import com.example.domain.CAST_INT_EXCEPTION
import com.example.domain.toIntOrThrow
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringUtilsTest {

	@Test
	fun `정수 캐스팅 실패 테스트`() {
		// given
		val str = "string"

		try {
			// when
			str.toIntOrThrow()
		} catch (e: IllegalStateException) {
			// then
			assertThat(e.message).isEqualTo(CAST_INT_EXCEPTION)
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